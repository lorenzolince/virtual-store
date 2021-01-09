<?php
namespace App\Models\dao;
use \PDO;
use App\Models\conf\DatabaseConfig;
 

 class ArticlesDao  {
   
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

      public function save($artilce)
     {
       $stmt = $this->conn ->prepare("INSERT INTO articles (name, url,video,description, precio, precio_real, categoria, cantidad) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
       error_log ("########## INSERT INTO articles  ############");
       $this->conn ->beginTransaction();
       $stmt->bindParam(1, $artilce->name);
       $stmt->bindParam(2, $artilce->url);
       $stmt->bindParam(3, $artilce->video);
       $stmt->bindParam(4, $artilce->description);
       $stmt->bindParam(5, $artilce->precio);
       $stmt->bindParam(6, $artilce->precioReal);
       $stmt->bindParam(7, $artilce->categoria);
       $stmt->bindParam(8, $artilce->cantidad);
       $stmt->execute();
       $id = $this->conn->lastInsertId();
       $this->conn ->commit();
       return  $id;
      }

    public function update($artilce)
     {
       $stmt = $this->conn ->prepare("UPDATE articles set name = ? , description = ?, precio= ?,
        precio_real =? ,  categoria = ?,  cantidad =?  WHERE id = ?");
       error_log ("########## UPDATE articles  ############");
       $this->conn ->beginTransaction();
       $stmt->bindParam(1, $artilce->name);
       $stmt->bindParam(2, $artilce->description);
       $stmt->bindParam(3, $artilce->precio);
       $stmt->bindParam(4, $artilce->precioReal);
       $stmt->bindParam(5, $artilce->categoria);
       $stmt->bindParam(6, $artilce->cantidad);
       $stmt->bindParam(7, $artilce->id);
       $stmt->execute();
       $this->conn ->commit();
       return  $artilce->id;
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
       $stmt = $this->conn ->prepare("SELECT id , name, url, video, description, precio, precio_real as precioReal, categoria, cantidad FROM `articles`");
       $stmt->execute();
       $articles = $stmt->fetchAll(PDO::FETCH_OBJ);
       return  $articles;
     }
      public function byCategory($category)
     {
       $stmt = $this->conn ->prepare("SELECT id , name, url, video, description, precio, precio_real as precioReal, categoria, cantidad FROM `articles` WHERE `categoria`= ?");
       $stmt->bindParam(1, $category);
       $stmt->execute();
       $articles = $stmt->fetchAll(PDO::FETCH_OBJ);
       return  $articles;
     }

}