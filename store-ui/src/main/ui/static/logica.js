export default {
    data:{
        cart: []
    },
    add(producto){
        var busqueda = _.find(this.data.cart, ['id',producto.id])
        //si no está
        if(typeof busqueda != 'object'){
            this.data.cart.push({
                id:producto.id,
                nombre :producto.nombre,
                precio:producto.precio,
                img:'producto.jpg',
                qty:1,
            })
        }
    },
    inc(producto){
          var busqueda = _.find(this.data.cart, ['id',producto.id])
            //si está
          if(typeof busqueda == 'object'){
              //si está en el carrito dame el indice en la posición del array
            var index = _.indexOf(this.data.cart, busqueda)
             this.data.cart[index].qty++
           }
    },
    dec(producto){
    var busqueda = _.find(this.data.cart, ['id',producto.id])
            //si está
          if(typeof busqueda == 'object'){
              //si está en el carrito dame el indice en la posición del array
            var index = _.indexOf(this.data.cart, busqueda)
            if(this.data.cart[index].qty==1){
                this.data.cart.splice(index, 1);
            }else{
                this.data.cart[index].qty--
            }
           }
    }
}