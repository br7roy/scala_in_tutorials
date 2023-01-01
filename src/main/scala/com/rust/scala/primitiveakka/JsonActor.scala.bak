package com.rust.scala.primitiveakka

import scala.actors.Actor

case class Message(content: String, sender: Actor)

/**
  * akka之间的互相调用
  *
  * @author Rust
  */
class JsonActor extends Actor {
  val t: Thread = Thread.currentThread()

  def getT(): Thread = t

  override def act(): Unit = {
    while (true) {
      receive {
        case Message(content, actor) => println("JsonActor received " + content)
          sender ! "Json in Shanghai"
          sender ! 1000
      }
    }

  }
}

class RoyActor(rayActor: JsonActor) extends Actor {
  val t: Thread = Thread.currentThread()

  def getT(): Thread = t

  override def act(): Unit = {
    rayActor ! Message("hello I am RoyActor", RoyActor.this)
    while (true) {
      receive {
        case resp: String => println("Roy received " + resp)
        case resp: Int => println("cash:" + resp)
      }
    }
  }
}

object ActorDemo3 {
  def main(args: Array[String]): Unit = {
    val rayActor = new JsonActor
    val royActor = new RoyActor(rayActor)
     rayActor.start()
     royActor.start()
  }
}