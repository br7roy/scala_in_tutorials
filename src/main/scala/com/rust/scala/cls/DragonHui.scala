package com.rust.scala.cls

/**
  * @author Rust
  */
private [cls]class DragonHui(var name:String,age :Int=30) {
  override def toString: String = this.name+this.age
}
object DragonHui{
  def main(args: Array[String]): Unit = {
    val dh:DragonHui = new DragonHui("nanhui")
    dh.name="zhangsan"
    println(dh)
"exercise"

  }
}

