<?php
namespace App\Models\service;

use App\Models\dao\UserDao;


class UserService  {
    private  $userDao;
     
     function __construct() {
      $this->$userDao = new UserDao();
    }


   public function createUser($user)
     {
     $user->role="ROLE_USER";
     error_log ("###### createUser ###########");
     error_log ("email:  ".$user->email);
     error_log ("password:  ". md5($user->password) );
     error_log ("nombre:  ".$user->nombre);
     error_log ("celular:  ".$user->celular);
     error_log ("direccion:  ".$user->direccion);
     error_log ("role:  ".$user->role);
     $this->$userDao->createUser($user);
    }

}