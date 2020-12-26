package com.newczl.libnavcompiler

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.google.auto.service.AutoService
import com.newczl.libnavannotation.ActivityDestination
import com.newczl.libnavannotation.FragmentDestination
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import javax.tools.StandardLocation
import kotlin.math.abs
/**
* NavProcessor.kt 类：注解处理器，生成导航栏文件文件
* @author czl
* created at 2020/12/26 9:09
*/
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(
    "com.newczl.libnavannotation.FragmentDestination",
    "com.newczl.libnavannotation.ActivityDestination"
)
class NavProcessor : AbstractProcessor() {

    companion object {
        @JvmStatic
        val OUTPUT_FILE_NAME = "destnation.json"
    }

    private lateinit var message: Messager
    private lateinit var files: Filer


    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        message = processingEnv!!.messager
        files = processingEnv.filer
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        val fragmentsAnnotatedWith =
            roundEnv!!.getElementsAnnotatedWith(FragmentDestination::class.java)

        val activityAnnotatedWith =
            roundEnv.getElementsAnnotatedWith(ActivityDestination::class.java)
        //不为空才进行处理
        if (fragmentsAnnotatedWith.isNotEmpty() || activityAnnotatedWith.isNotEmpty()) {
            val destMap: HashMap<String, JSONObject> = HashMap<String, JSONObject>()
            handleDestination(fragmentsAnnotatedWith, FragmentDestination::class.java, destMap)
            handleDestination(activityAnnotatedWith, ActivityDestination::class.java, destMap)
            //生成文件到 app/src/main/assets
            val resource =
                files.createResource(StandardLocation.CLASS_OUTPUT, "", OUTPUT_FILE_NAME)
            //文件路径
            val resourcePath = resource.toUri().path
            message.printMessage(Diagnostic.Kind.NOTE, "resourcePath: $resourcePath")
            //app路径
            val appPath = resourcePath.substring(0, resourcePath.indexOf("app") + 4)
            //assert路径
            val assertPath = appPath + "src/main/assets"
            val file = File(assertPath)
            //不存在则创建目录
            if (!file.exists()) {
                file.mkdirs()
            }
            val outPutFile = File(file, OUTPUT_FILE_NAME);
            if (outPutFile.exists()) {
                outPutFile.delete()
            }
            outPutFile.createNewFile()
            val content = JSON.toJSONString(destMap);
            val outputStreamWriter = OutputStreamWriter(FileOutputStream(outPutFile), "UTF-8")
            outputStreamWriter.use {
                outputStreamWriter.write(content)
                outputStreamWriter.flush()
            }
        }
        return true
    }

    /**
     * 处理注释
     */
    private fun handleDestination(
        fragmentsAnnotatedWith: Set<Element>,
        java: Class<out Annotation>,
        destMap: java.util.HashMap<String, JSONObject>
    ) {
        fragmentsAnnotatedWith.forEach {
            val typeElement = it as TypeElement
            val className = typeElement.qualifiedName.toString()
            var pageUrl = ""
            val id = abs(className.hashCode())
            var needLogin = false
            var asStarter = false
            var isFragment = false
            when (val annotation = typeElement.getAnnotation(java)) {
                is FragmentDestination -> {
                    pageUrl = annotation.pageUrl
                    asStarter = annotation.asStart
                    needLogin = annotation.needLogin
                    isFragment = true
                }
                is ActivityDestination -> {
                    pageUrl = annotation.pageUrl
                    asStarter = annotation.asStart
                    needLogin = annotation.needLogin
                    isFragment = false
                }
            }
            if (destMap.containsKey(pageUrl)) {
                message.printMessage(Diagnostic.Kind.ERROR, "不同的页面不允许使用相同的PageUrl:$className")
            } else {
                val json = JSONObject()
                json["id"] = id
                json["needLogin"] = needLogin
                json["asStarter"] = asStarter
                json["pageUrl"] = pageUrl
                json["className"] = className
                json["isFragment"] = isFragment
                destMap[pageUrl] = json
            }
        }
    }


}