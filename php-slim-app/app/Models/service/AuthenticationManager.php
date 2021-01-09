<?php
namespace App\Models\service;
use App\Models\dao\UserDao;
use \Exception;
class AuthenticationManager  {
     private  $userDao;
     
     function __construct() {
       $this->$userDao = new UserDao();
    }

   public function authenticate($username,$password)
     {
     error_log ("###### authenticate ###########");
     error_log ("username:  ".$username);
     error_log ("password:  ".$password);
     $user =$this->$userDao->getUserByUsername($username);
     $response = array();
     $model = array();
     $response["username"] = $username;
     $response["nombre"] =   $user-> NAME;
     $response["celular"] = $user-> CELL_PHONE;
     $response["direccion"] = $user-> ADDRESS;
     $model["user"] =  $response;
     $model["roles"] =  $user->roles;
     error_log(" PASSWORD DB " . $user-> PASSWORD);
     if (md5($password) === $user-> PASSWORD) {
         error_log("good PASSWORD");
          $_SESSION["tock"]= "Bearer " . session_id();
          $model["token"] = $_SESSION["tock"];
          $_SESSION["roles"]= $user->roles;
     }else {
      throw new Exception('INVALID PASSWORD');
     }
     return  $model;
     }

}