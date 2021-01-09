<?php
namespace App\Models\Middleware;
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\response\ResponseStore;

 class Authenticate  
{
  
 public function __invoke($request, $response, $next) : Response
    {
     $headers = apache_request_headers();
     $msg = array();
     $res=new ResponseStore();

     if (isset($headers['Authorization'])) {
        $token = $headers['Authorization'];
       if (!($token == $_SESSION["tock"])) { 
            $msg["error"] = true;
            $msg["message"] = "Acceso denegado. Token invalido";
            $response->getBody()->write(json_encode($msg));
            return $res->echoResponse(403, $response);
       } else {
           $response = $next($request, $response);   
           return $response;
        }
    } else {
        $msg["error"] = true;
        $msg["message"] = "Falta token de autorizacion";
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(402, $response);
    }
 }

}