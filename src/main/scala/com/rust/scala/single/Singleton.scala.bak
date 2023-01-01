package com.rust.scala.single

import java.util
import java.util.concurrent.atomic.LongAccumulator
import java.util.function.Consumer

/**
  * @author Rust
  */
object Singleton {

  @volatile private var instance: LongAccumulator = _

  def applyAsLong(x: Long, y: Long): Long = {
    x + y
  }

  def getInstance: LongAccumulator = {
    if (instance == null) {
      synchronized {
        if (instance == null) {
          instance = TestLongAcc.getInstance()
        }
      }
    }
    instance
  }

  def main(args: Array[String]): Unit = {
    val ls = new util.ArrayList[LongAccumulator]
    for (_ <- 1 to 10) {
      val ins = getInstance
      println(ins.getClass.toString)
      ls.add(ins)
    }

    ls.forEach(new Consumer[LongAccumulator] {
      override def accept(t: LongAccumulator): Unit = {
        println(t.hashCode())
      }
    })


  }
}
