<?php
namespace App\Models\Middleware;
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\response\ResponseStore;

 class ValidRoles  
{
  
 public function __invoke($request, $response, $next) : Response
    {
     $msg = array();
     $res=new ResponseStore();
     $basePath = $request->getUri()->getBasePath();
      $roles =$_SESSION["roles"];
      $isRoleAdmin = false;
      switch ($basePath) {
      case "/api/register":
        if(isset($roles)) {
       foreach($roles as $role) {
         if($role  === 'ROLE_ADMIN'){
          $isRoleAdmin =true;
          }
        }
    }
        break;
       case "/api/articles":
         if(isset($roles)) {
       foreach($roles as $role) {
         if($role  === 'ROLE_ADMIN'){
          $isRoleAdmin =true;
          }
        }
    }
        break;
           case "/api/ventas":
         if(isset($roles)) {
       $path = $request->getUri()->getPath();
       $roleValid = 'ROLE_ADMIN';
       if($path === "save"){
        $roleValid = 'ROLE_USER';
       }
       foreach($roles as $role) {
         if($role  === $roleValid){
          $isRoleAdmin =true;
          }
        }
    }
        break;
}
    
     if ($isRoleAdmin) {
         $response = $next($request, $response);   
         return $response;
    } else {
        $msg["error"] = true;
        $msg["message"] = "no esta autorizado";
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(401, $response);
    }
   }
  function startsWith ($string, $startString) 
{ 
    $len = strlen($startString); 
    return (substr($string, 0, $len) === $startString); 
}
}