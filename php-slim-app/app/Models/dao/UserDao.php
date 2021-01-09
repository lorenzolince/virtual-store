<?php
namespace App\Models\dao;
use \PDO;
use App\Models\conf\DatabaseConfig;
 class UserDao  {
   
   private $conn;
 
    function __construct() {
        $db = new DatabaseConfig();
        $this->conn = $db->connect();
    }
 public function getUserByUsername($username)
     {
     
       $stmt = $this->conn ->prepare("SELECT * FROM `USER` WHERE `USERNAME`= ?");
       $stmt->bindParam(1, $username);
       $stmt->execute();
       $user = $stmt->fetch(PDO::FETCH_OBJ);
       error_log ("id:  ". $user-> id);
       $stmt = $this->conn ->prepare("SELECT roles FROM `User_roles` WHERE id = ?");
       $stmt->bindParam(1, $user-> id);
       $stmt->execute();
       $rolesDB = $stmt->fetchAll(PDO::FETCH_OBJ);
       $roles = array();
       foreach($rolesDB as $role) {
          $roles[] = $role -> roles;
        }
       $user->roles = $roles;
      
       return $user;
     }

      public function createUser($user)
     {
       $stmt = $this->conn ->prepare("INSERT INTO USER (USERNAME, NAME,CELL_PHONE,ADDRESS, PASSWORD) VALUES (?, ?, ?, ?, ?)");
       error_log ("########## INSERT INTO User  ############");
       $this->conn ->beginTransaction();
       $stmt->bindParam(1, $user->email);
       $stmt->bindParam(2, $user->nombre);
       $stmt->bindParam(3, $user->celular);
       $stmt->bindParam(4, $user->direccion);
       $stmt->bindParam(5, md5($user->password));
       $stmt->execute();
       error_log ("########## lastInsertId  ############  " . $this->conn->lastInsertId());
       $id = $this->conn->lastInsertId();
       if($id > 0){
         $stmt = $this->conn ->prepare("INSERT INTO User_roles (id,roles) VALUES (?, ?)");
         $stmt->bindParam(1, $id);
         $stmt->bindParam(2, $user->role); 
         $stmt->execute();
         }
         $this->conn ->commit();
      }

}