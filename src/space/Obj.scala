package space

import java.awt.Color;

object Obj {
  def apply(I: Obj.I, M: Obj.M) = new Obj(I,M)
  def apply(p: Vector[Pt], f: Vector[Face], c: Vector[Color], pos: Force, vel: Force, acc: Force, m: Map[Pt,Pt] = Map()) = new Obj(I(p,f,c),M(pos,vel,acc,m))
  
  object I {
    def apply(p: Vector[Pt], f: Vector[Face], c: Vector[Color]) = new I(p,f,c);
  }
  object M {
    def apply(pos: Force, vel: Force, acc: Force, m: Map[Pt,Pt]) = new M(pos,vel,acc,m);
  }
  
  class I(val points: Vector[Pt], val faces: Vector[Face], val colors: Vector[Color]) {}
  class M(val pos: Force, val vel: Force, val acc: Force, val ptMap: Map[Pt,Pt]) {
    val D = pos.D
  }
}

class Obj(val I: Obj.I, val M: Obj.M){
  def step(m: Option[(Int,Force)]) = Obj(I,
    m match{
      case Some(x) => Obj.M(if(x._1==0) x._2 else M.vel(M.pos),if(x._1==1) x._2 else M.acc(M.vel),if(x._1==2) x._2 else M.acc,M.ptMap)
      case None => Obj.M(M.vel(M.pos),M.acc(M.vel),M.acc,M.ptMap)
    }
  )
}
