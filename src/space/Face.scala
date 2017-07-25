package space

object Face {
  def apply(I: Face.I, M: Face.M) = new Face(I,M)
  def apply(c: Vector[Pt]) = new Face(I(c),M())
  
  object I {
    def apply(c: Vector[Pt]) = new I(c);
  }
  object M {
    def apply() = new M();
  }
  
  class I(val corners: Vector[Pt]) {}
  class M() {}
}

class Face(val I: Face.I, val M: Face.M) {
  val D = I.corners(0).D
  def apply(m: Map[Pt,Pt]) = I.corners.map(m(_))
  def shard = (I.corners.combinations(I.corners.length-1).map{v=>Face(Face.I(v:+center),Face.M())},center)
  val center = Pt(Range(1,I.corners.length,1).foldRight(I.corners(0).sub)((i,z)=>I.corners(i).addFrac(z,I.corners.length)))
}