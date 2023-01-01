package com.rust.scala.akka.http
import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration.DurationInt

/**
 * @author tak
 */
class HttpActor extends Actor {
  // 在 actor 中使用 Akka HTTP 需要定义这些变量
  implicit val system = context.system
  implicit val materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  def receive = {
    case url: String =>
      val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = url))
      val result: Future[String] = response.flatMap { res =>
        res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.utf8String)
      }
      result.map(x => println(x))
    case "terminate" => context.system.terminate()
  }
}



object HttpActor{
  def main(args: Array[String]): Unit = {

    // 创建 actorSystem
    val actorSystem = ActorSystem("myActorSystem")

    // 创建 HttpActor
    val httpActor = actorSystem.actorOf(Props[HttpActor], "httpActor")

    // 发送请求
    httpActor ! "https://www.baidu.com"
    actorSystem.scheduler.scheduleOnce(5.seconds, httpActor, "terminate")
    actorSystem.terminate()

  }
}



