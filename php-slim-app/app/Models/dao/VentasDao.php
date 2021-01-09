<?php
namespace App\Models\dao;
use \PDO;
use App\Models\conf\DatabaseConfig;
 

 class VentasDao  {
   
   private $conn;
 
    function __construct() {
        $db = new DatabaseConfig();
        $this->conn = $db->connect();
    }

 public function delete($_id)
     {
     
       $stmt = $this->conn ->prepare("DELETE FROM `articles` WHERE `id`= ?");
       error_log ("########## DELETE  Articles  ############");
       $this->conn ->beginTransaction();
       $stmt->bindParam(1, $_id);
       $stmt->execute(); 
       $this->conn ->commit();
     }

      public function save($ventas)
     {
       $stmt = $this->conn ->prepare("INSERT INTO `ventas`(nombre_comprador, celular, direccion, estado, fecha, total) 
        VALUES (?, ?, ?, ?, SYSDATE(), ?)");
    
       $this->conn ->beginTransaction();
       $stmt->bindParam(1, $ventas->nombreComprador);
       $stmt->bindParam(2, $ventas->celular);
       $stmt->bindParam(3, $ventas->direccion);
       $stmt->bindParam(4, $ventas->status);
       $stmt->bindParam(5, $ventas->total);
       $stmt->execute();
       $id = $this->conn->lastInsertId();
       if($id > 0){
         foreach($ventas->productos as $producto) {
       $stmt = $this->conn ->prepare("INSERT INTO `productos`(id_venta, id_articulo, cantidad) 
        VALUES (?, ?, ?)");
       $stmt->bindParam(1, $id);
       $stmt->bindParam(2, $producto->idArticles);
       $stmt->bindParam(3, $producto->cantidad);
       $stmt->execute();
        }
      }
       $this->conn ->commit();
       return  $id;
      }

       public function get($_id)
     {
       $stmt = $this->conn ->prepare("SELECT id , name, url, video, description, precio, precio_real as precioReal, categoria, cantidad FROM `articles` WHERE `id`= ?");
       $stmt->bindParam(1, $_id);
       $stmt->execute();
       $articles = $stmt->fetch(PDO::FETCH_OBJ);
       return  $articles;
     }

       public function getAll()
     {
       $stmt = $this->conn ->prepare("SELECT id ,nombre_comprador, celular, direccion, estado, fecha, total FROM `ventas`");
       $stmt->execute();
       $ventas = $stmt->fetchAll(PDO::FETCH_OBJ);
       return  $ventas;
     }
        public function getAllVentas()
     {
       $stmt = $this->conn ->prepare("SELECT
    v.id,
    v.nombre_comprador comprador,
    v.celular,
    v.direccion,
    v.fecha,
    v.estado,
    v.total total_venta,
    SUM(
        (
            p.cantidad *(a.precio - a.precio_real)
        )
    ) total_ganancia
FROM
    ventas v
INNER JOIN productos p ON
    p.id_venta = v.id
INNER JOIN articles a ON
    a.id = p.id_articulo
GROUP BY
    v.id,
    v.nombre_comprador,
    v.celular,
    v.direccion,
    v.fecha,
    v.estado,
    v.total");
       $stmt->execute();
       $ventas = $stmt->fetchAll(PDO::FETCH_OBJ);
       return  $ventas;
     }

        public function getProductosByVenta($id)
     {
       $stmt = $this->conn ->prepare("SELECT
    p.id,
    a.name AS producto,
    p.cantidad,
    a.precio AS precio_unitario,
    (p.cantidad * a.precio) AS precio_por_cantidad,
    a.precio_real,
    (a.precio - a.precio_real) AS ganancia_unitaria,
    p.cantidad *(a.precio - a.precio_real) AS ganancia_por_cantidad
FROM
    ventas v
INNER JOIN productos p ON
    p.id_venta = v.id
INNER JOIN articles a ON
    a.id = p.id_articulo
WHERE
    v.id =?");
       $stmt->bindParam(1, $id);
       $stmt->execute();
       $articles = $stmt->fetchAll(PDO::FETCH_OBJ);
       return  $articles;
     }

      public function updateEstadoVenta($id,$estado)
     {
        $stmt = $this->conn ->prepare("UPDATE ventas SET estado = ?  WHERE id = ? ");
        $this->conn ->beginTransaction();
        $stmt->bindParam(1, $estado);
        $stmt->bindParam(2, $id);
        $stmt->execute();
        $this->conn ->commit();
      
      }

}