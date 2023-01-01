package com.rust.scala.file

import java.io._
import java.nio.CharBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.function.Consumer

//import javax.xml.ws.ServiceMode

import scala.io.Source

/**
  * 读取词法但愿和数字
  *
  * @author Rust
  */
object ReadNumberic {
  def main(args: Array[String]): Unit = {
    //  读取所有以空格隔开的词法单元
    val re = ReadNumberic
    val source = Source.fromFile(re.getClass.getResource("/myFile.txt").toURI)
    val res = source.mkString.split("\\s+")
    println(res.mkString)

    //  字符串转数字
    val numbers = for (n <- res) yield n.toInt

    //  或者用map
    val ns = res.map(_.toInt)

    //  从scala.io.StdIn读取数字
    import scala.io.{StdIn => sin}
    println("how are you?")
    val age = sin.readInt()
    //  或者使用readDouble readLong
    //  如果有空格或者非数字会报异常


    //------------
    //  从URL读取源文件
    val source1 = Source.fromURL("http://horstmann.com", "UTF-8")
    //  从给定的字符串读取，对调试很有用
    val source2 = Source.fromString("Hello,World!")
    //  从标准输入读取
    val source3 = Source.stdin


    //------------
    //  读取二进制文件
    //  scala没有原生读取二进制的方法，使用java类库
    val file = new File("/myFile.txt")
    val in = new FileInputStream(file)
    val bytes = new Array[Byte](file.length().toInt)
    in.read(bytes)
    in.close()


    //------------
    //  写入文本文件
    //  没有原生，使用java
    val out = new PrintWriter("/myFile.txt")
    val message = "Hello World!"
    val cb = CharBuffer.allocate(message.length)
    cb.put(message)
    out.write(cb.array())
    out.flush()

    for (i <- 1 to 100) println(i)

    out.close()


    //--------------
    //  访问目录
    //  没有原生，使用java
    import java.nio.file._
    val dirName = ""
    val entries = Files.walk(Paths.get(dirName))
    try {
      entries.forEach(new Consumer[Path] {
        override def accept(p: Path): Unit = {
          //  处理

        }
      })
    } finally {
      entries.close()
    }

    //-------------
    //  序列化
//    @SerialVersionUID(1L) class Person extends Serializable

    //  使用默认序列号
    class Person2 extends Serializable


  }
}
