package space

object Face {
  def apply(c: Vector[Pt]) = new Face(c)
}

class Face(val corners: Vector[Pt]) {
  val D = corners(0).D
  val edgeLength = corners.combinations(2).foldRight(0d)((e,t)=>e(0).disTo(e(1))+t)
  val center = Pt(Range(1,corners.length,1).foldRight(corners(0).sub)((i,z)=>corners(i).addFrac(z,corners.length)))
  
  def shard(f: Function[Face,Boolean]): List[Face] = {if(f(this)) (Face(corners.combinations(2).map{p=>p(0).avg(p(1))}.toVector)::corners.map(p=>Face(corners.map{c=>c.avg(p)})).toList).flatMap{_.shard(f)} else List(this)}
  def view(fov: Double, f: Function[Face,Boolean], objF: Force, camF: Force): List[Face] = shard(f).map{_.view(fov,objF,camF)}
  def view(fov: Double, objF: Force, camF: Force) = Face(corners.map{p=>camF(objF(p)).view(fov)})
}