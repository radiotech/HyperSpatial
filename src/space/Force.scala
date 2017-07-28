package space

object Force {
  def apply(r: Rotation, xr: Rotation, t: Translation) = new Force(r,xr,t)
  def apply(a1: Int, a2: Int, r: Double, v: Vector[Double]) = new Force(Rotation(v.length)(a1,a2,r),Rotation(v.length)(a2,a1,r),Translation(v))
  def apply(d: Int, a1: Int, a2: Int, r: Double) = new Force(Rotation(d)(a1,a2,r),Rotation(d)(a2,a1,r),Translation.zeros(d))
  def apply(v: Vector[Double]) = new Force(Rotation.zeros(v.length),Rotation.zeros(v.length),Translation(v))
  def apply(d: Int, axis: Int, dis: Double) = new Force(Rotation.zeros(d),Rotation.zeros(d),Translation(Vector.tabulate(d)(i=>if(i==axis) dis else 0)))
  def zeros(d: Int) = new Force(Rotation.zeros(d),Rotation.zeros(d),Translation.zeros(d))
  
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
    def zeros(d: Int) = Translation(Vector.fill(d)(0.0));
  }
  
  class Rotation(val axes: Vector[Pt]) {
    val D = axes.length
    def apply(p: Pt): Pt = Pt(axes.indices.toVector.map{i => p.dot(axes(i))})
    def apply(ps: Vector[Pt]): Vector[Pt] = ps.map(apply(_))
    def apply(r: Rotation): Rotation = Rotation(apply(r.axes))
  }
  
  class Translation(val axes: Vector[Double]) {
    val D = axes.length
    def apply(p: Pt) = Pt(Range(0,Math.min(p.D,D)).toVector.map{i => p(i)+axes(i)})
    def apply(ps: List[Pt]): List[Pt] = ps.map(apply(_))
    def apply(t: Translation) = Translation(Range(0,Math.min(t.D,D)).toVector.map{i => t.axes(i)+axes(i)})
    def x(p: Pt) = Pt(Range(0,Math.min(p.D,D)).toVector.map{i => p(i)-axes(i)})
    def x(t: Translation) = Translation(Range(0,Math.min(t.D,D)).toVector.map{i => t.axes(i)-axes(i)})
  }
}

class Force(val r: Force.Rotation, val xr: Force.Rotation, val t: Force.Translation) {
  val D = r.D;
  def apply(p: Pt) = t(r(p))
  def apply(f: Force) = Force(r(f.r),xr(f.xr),t(f.t))
  def x(p: Pt) = t.x(xr(p))
  def x(f: Force) = Force(xr(f.r),r(f.xr),t.x(f.t))
}