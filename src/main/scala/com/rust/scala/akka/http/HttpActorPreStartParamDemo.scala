package com.rust.scala.akka.http

import akka.actor.TypedActor.{context, self}
import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import akka.util.ByteString
import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString
import jdk.internal.net.http.common.Log.headers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration.DurationInt
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.postfixOps

/**
 * @author tak
 */
class HttpActorPreStartParamDemo(p1:String) extends Actor {

  implicit val system = context.system
  // 需要用到 actor materializer 来处理流
  implicit val materializer = ActorMaterializer
  override def preStart() = {
    // 在 preStart 方法中使用参数
    // ……
    println(s"canshu1:$p1")
     Http().singleRequest(HttpRequest(uri = s"https://baidu.com/s?wd=$p1")).pipeTo(self)
  }

  def receive = {
    case  HttpResponse(StatusCodes.OK, headers, entity, _) =>
      println("ok")
      entity.toStrict(5 seconds).map(_.data.utf8String)
        .foreach(println)
    // 处理 HTTP 响应，并执行你的业务逻辑
  }
  // ……
}
object HttpActorPreStartParamDemo{
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("client-system")
    implicit val materializer = ActorMaterializer

    // 创建自定义 actor
    val actor = system.actorOf(Props(new HttpActorPreStartParamDemo("hello")), "my-actor")
    actor ! "start"

  }
}
