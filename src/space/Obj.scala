package space

import java.awt.Color;

object Obj {
  
  def apply(I: Obj.I, M: Obj.M) = new Obj(I,M)
  def apply(p: Vector[Pt], f: Vector[Face], c: Vector[Color], fs: Forces, m: Map[Pt,Pt] = Map()) = new Obj(I(p,f,c),M(fs,m))
  
  object I {
    def apply(p: Vector[Pt], f: Vector[Face], c: Vector[Color]) = new I(p,f,c);
  }
  object M {
    def apply(fs: Forces, m: Map[Pt,Pt]) = new M(fs,m);
  }
  object Forces {
    def apply(fs: List[Force], ts: Set[(Int,Int)]) = new Forces(fs,ts)
    def zeros(d: Int) = new Forces(List.tabulate(8)(_=>Force.zeros(d)), Set())
  }
  
  class I(val points: Vector[Pt], val faces: Vector[Face], val colors: Vector[Color]) {}
  class M(val fs: Forces, val ptMap: Map[Pt,Pt]) {
    val D = fs.pos.D
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

class Obj(val I: Obj.I, val M: Obj.M){
  def step(f: Function[Obj.Forces,Obj.Forces]) = Obj(I,Obj.M(f(M.fs).step,M.ptMap))
}
