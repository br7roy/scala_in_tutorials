package com.rust.scala.file

import java.io.File
import java.net.URL

import javax.script.{ScriptEngine, ScriptEngineManager}


/**
  * 进程控制
  * 使用scala编写shell脚本
  *
  * @author Rust
  */
object ProcessControl {
  def main(args: Array[String]): Unit = {
    //  scala.sys.process包含了一个从字符串到ProcessBuilder对象的隐式转换
    //  !操作符执行的就是这个ProcessBuilder对象
    import scala.sys.process._
    //  显示上层目录的所有文件
    "ls -al ..".!

    //  操作符返回的是程序执行结果的返回值，成功是0，其他非0

    //  如果使用!!，输出会以字符串形式出现
    "ls -al ..".!!

    //------------
    //  管道
    ("ls -al /" #| "grep hello").!


    //------------
    //  重定向
    ("ls -al /soft/hadoop/" #> new File("hello.txt")).!

    //------------
    //  追加
    ("echo hello " #>> new File("/home/ubuntu/hello.txt")).!

    //------------
    //  把某个文件的内容作为输入，使用#<
    ("grep h" #< new File("/home/ubuntu/kk.txt")).!

    //------------
    //  使用URL重定向输入
    ("grep scala" #< new URL("http://horstmann.com/index.html")).!

    //------------
    //  也可以使用#&&
    ("grep scala" #< new URL("http://horstmann.com/index.html") #&& ("echo hello")).!

    //  总之在shell里用的操作符scala里 加#前缀，优先级是相同的


    //  如果要在不同的目录下运行进程，或者使用不同的环境变量，可以用Process对象的apply方法来构造ProcessBuilder
    //  给出命令和其实目录，以及一串(名称，值)对偶来设置环境变量
    val cmd = "ls"
    val dirName = "/soft/hadoop"
    val p = Process(cmd, new File(dirName), ("LANG", "en_US"))

    //  然后使用!操作符执行
    ("echo hehe" #| p).!


    //-------------
    //  在UNIX/Linux/Mac OS环境使用Scala编写Shell脚本，可以像这样开始

    /*

    #!/bin/sh
    exec scala s"$0" "$@"
    !#
    scala 命令.......


    */

    //  在java程序中也可以通过javax.script包的脚本集成功能来运行scala脚本
    //  像这样来获取脚本引擎
    val engine = new ScriptEngineManager().getEngineByName("scala")


  }


}
