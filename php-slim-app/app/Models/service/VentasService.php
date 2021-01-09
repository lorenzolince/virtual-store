<?php
namespace App\Models\service;

use App\Models\dao\VentasDao;


class VentasService  {
    private  $ventasDao;
     
     function __construct() {
      $this->$ventasDao = new VentasDao();
    }


   public function save($ventas)
     {
     
     error_log ("###### ventas ###########");
     error_log ("nombreComprador:  ".$ventas->nombreComprador);
     error_log ("status:  ".$ventas->status);
     error_log ("fecha:  ".$ventas->fecha);
     error_log ("celular:  ".$ventas->celular);
     error_log ("direccion:  ".$ventas->direccion);
     error_log ("total:  ".$ventas->total);
    foreach($ventas->productos as $producto) {
           error_log ("producto id:  ".$producto->idArticles);
           error_log ("producto cantidad:  ".$producto->cantidad);
        }
     return $this->$ventasDao->save($ventas);
    }

     public function getAll()
     {
      return $this->$ventasDao->getAll();
     }

        public function getEstados()
     {
     $status = array('PROCESO', 'VENDIDO', 'CANCELADO','ENTREGADO');
      return $status ;
     }
      public function getAllVentas()
     {
      return $this->$ventasDao->getAllVentas();
     }

      public function getProductosByVenta($id)
     {
      return $this->$ventasDao->getProductosByVenta($id);
     }

       public function updateEstadoVenta($id,$estado)
     {
      $this->$ventasDao->updateEstadoVenta($id,$estado);
    }

}