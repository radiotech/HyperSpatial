package space

object Force {
  def apply(r: Rotation, t: Translation) = new Force(r,t)
  def apply(a1: Int, a2: Int, r: Double, v: Vector[Double]) = new Force(Rotation(v.length)(a1,a2,r),Translation(v))
  def apply(d: Int, a1: Int, a2: Int, r: Double) = new Force(Rotation(d)(a1,a2,r),Translation.zeros(d))
  def apply(v: Vector[Double]) = new Force(Rotation.zeros(v.length),Translation(v))
  def zeros(d: Int) = new Force(Rotation.zeros(d), Translation.zeros(d))
  
  object Rotation {
    def apply(d: Int)(a1: Int, a2: Int, r: Double) = new Rotation(Vector.tabulate(d){ a =>
      if(a == a1){
        Pt(Vector.tabulate(d){v=>if(v == a1) Math.cos(r) else if(v == a2) Math.sin(r) else 0d})
      } else if(a == a2) {
        Pt(Vector.tabulate(d){v=>if(v == a2) Math.cos(r) else if(v == a1) -Math.sin(r) else 0d})
      } else {
        Pt(Vector.tabulate(d){v=>if(v == a) 1 else 0d})
      }
    })
    def apply(ps: Vector[Pt]) = new Rotation(ps)
    def zeros(d: Int) = new Rotation(Vector.tabulate(d){ a =>
      Pt(Vector.tabulate(d){v=>if(v == a) 1 else 0d})
    })
  }
  object Translation {
    def apply(ps: Vector[Double]) = new Translation(ps)
    def apply(x: Double) = new Translation((x::Nil).toVector)
    def apply(x: Double, y: Double) = new Translation(Vector(x,y))
    def apply(x: Double, y: Double, z: Double) = new Translation(Vector(x,y,z))
    def apply(x: Double, y: Double, z: Double, w: Double) = new Translation(Vector(x,y,z,w))
    def zeros(d: Int) = Translation(Vector.fill(d)(0.0));
  }
  
  class Rotation(val axes: Vector[Pt]) {
    val D = axes.length
    def apply(i: Int) = axes(i)
    def apply(p: Pt, a: Int) = p.dot(axes(a))
    def apply(p: Pt): Pt = Pt(axes.indices.toVector.map{i => p.dot(axes(i))})
    def apply(ps: Vector[Pt]): Vector[Pt] = ps.map(apply(_))
    def apply(r: Rotation): Rotation = Rotation(apply(r.axes))
  }
  
  class Translation(val axes: Vector[Double]) {
    val D = axes.length
    def apply(i: Int) = axes(i)
    def apply(p: Double, a: Int) = p+axes(a)
    def apply(p: Pt) = Pt(Range(0,Math.min(p.D,D)).toVector.map{i => p(i)+axes(i)})
    def apply(ps: List[Pt]): List[Pt] = ps.map(apply(_))
    def apply(t: Translation): Translation = Translation(Range(0,Math.min(t.D,D)).toVector.map{i => t(i)+axes(i)})
  }
}

class Force(val r: Force.Rotation, val t: Force.Translation) {
  val D = r.D;
  def apply(p: Pt, a: Int) = t(r(p,a),a)
  def apply(p: Pt) = t(r(p))
  def apply(f: Force) = Force(r(f.r),t(f.t))
}