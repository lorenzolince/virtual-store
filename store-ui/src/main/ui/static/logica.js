export default {
  data: {
    cart: []
  },
  add(producto) {
    var busqueda = _.find(this.data.cart, ['id', producto.id])
     if(producto.cantidad > 0)
    if (typeof busqueda != 'object') {
      this.data.cart.push({
        id: producto.id,
        name: producto.name,
        precio: producto.precio,
        url: producto.url,
        cantidad: producto.cantidad,
        qty: 1
      })
    }
  },
  inc(producto) {
    var busqueda = _.find(this.data.cart, ['id', producto.id])
    //si está
    if (typeof busqueda == 'object') {
      //si está en el carrito dame el indice en la posición del array
      var index = _.indexOf(this.data.cart, busqueda)
      if(this.data.cart[index].cantidad > this.data.cart[index].qty){
      this.data.cart[index].qty++
      }
    }
  },
  dec(producto) {
    var busqueda = _.find(this.data.cart, ['id', producto.id])
    //si está
    if (typeof busqueda == 'object') {
      //si está en el carrito dame el indice en la posición del array
      var index = _.indexOf(this.data.cart, busqueda)
      if (this.data.cart[index].qty == 1) {
        this.data.cart.splice(index, 1)
      } else {
        this.data.cart[index].qty--
      }
    }
  },remove(){
    this.data.cart=[]
  }
}
