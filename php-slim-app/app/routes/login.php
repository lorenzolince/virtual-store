<?php
session_start();

use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\response\ResponseStore;
use App\Models\service\AuthenticationManager;



$app->post('/login' , function(Request $request, Response $response, array $args)  {
       error_log ("################## login ###################");
       $res=new ResponseStore();
        $msg = array();
        $msg["error"] = true;
        $auth = new AuthenticationManager();
        $data = $request->getParsedBody();
       try {
        $msg= $auth -> authenticate($data['username'],$data['password']);
       } catch(Exception $ex) { 
          error_log ($ex->getMessage());
          $msg = array();
          $msg["error"] = true;
          $msg["message"] = $ex->getMessage();
          $response->getBody()->write(json_encode($msg));
          return   $res->echoResponse(400,$response);
          
        }
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);
});


$app->get('/logout' , function(Request $request, Response $response, array $args)  {
	      session_destroy();
        session_start();
        $res=new ResponseStore();
        $msg = array();
        $msg["message"] = "logout";
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);
});
