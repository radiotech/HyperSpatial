package space

import java.awt.Color;

object Obj {
  
  def apply(f: Vector[Face], fs: Forces) = new Obj(f,fs)
  
  object Forces {
    def apply(fs: List[Force], ts: Set[(Int,Int)]) = new Forces(fs,ts)
    def zeros(d: Int) = new Forces(List.tabulate(8)(_=>Force.zeros(d)), Set())
  }
  
  class Forces(val fs: List[Force], val ts: Set[(Int,Int)]) {
    val pos = fs(0)
    def vel = fs(1)
    def acc = fs(2)
    def has(t: Int) = ts.exists(_._1 == t)
    def set(i: Int, f: Force) = Forces(fs.indices.toList.map(a=>if(a==i) f else fs(a)),ts.filter(_._2 != i))
    def add(i: Int, f: Force) = Forces(fs.indices.toList.map(a=>if(a==i) f(fs(a)) else fs(a)),ts)
    def push(t: Int, i: Int, f: Force) = {println("push "+(!has(t)));if(!has(t)) Forces(fs.indices.toList.map(a=>if(a==i) f(fs(a)) else fs(a)),ts+((t,i))) else this}
    def pull(t: Int, i: Int, f: Force) = {println("pull "+has(t));if(has(t)) Forces(fs.indices.toList.map(a=>if(a==i) f.x(fs(a)) else fs(a)),ts.filter(_._1 != t)) else this}
    private def s(xs: List[Force]): List[Force] = {
      if(xs.tail == Nil) xs else {
        val t = s(xs.tail)
        t.head(xs.head)::t
      }
    }
    def step = Forces(s(fs),ts)
    
    
  }
}

class Obj(val faces: Vector[Face], val fs: Obj.Forces){
  val D = fs.pos.D
  
  def step(f: Function[Obj.Forces,Obj.Forces]) = Obj(faces,f(fs).step)
  def shard(f: Function[Face,Boolean]) = Obj(faces.flatMap{_.shard(f)},fs)
  def view(fov: Double, f: Function[Face,Boolean], force: Force) = Obj(faces.flatMap{_.view(fov,f,fs.pos,force)},fs)
}
