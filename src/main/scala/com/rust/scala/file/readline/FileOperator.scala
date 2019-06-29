package com.rust.scala.file.readline

import java.nio.charset.{Charset, StandardCharsets}

import scala.io.Source

/**
  * @author Rust
  */
class FileOperator {
  def main(args: Array[String]): Unit = {

    //---------------
    //  读取行
    //  第一个参数可以是字符串或者是java.io.File
    //  如果确保读取文件是平台默认的字符编码，可以忽略第二个参数
    val source = Source.fromFile("myFile.txt", StandardCharsets.UTF_8.name())
    //  返回迭代器
    val lineIterator = source.getLines()

    for (i <- lineIterator) println(i)
    //  使用toArray方法或者toBuffer方法，将这些行放到数组或者数组缓冲当中
    val lines = source.getLines().toArray
    //  把整个文件读取成一个字符串
    val wholeContent = source.getLines().mkString


    //----------------
    //  读取字符
    for (c <- source) println(c)


    val buffer = source.buffered
    while (buffer.hasNext) {
      //  查看一个字符，但又不处理的方法
      if (buffer.head.equals('\n')) {
        //  开始处理字符
        buffer.next()
      }

    }
    source.close()

  }


}
