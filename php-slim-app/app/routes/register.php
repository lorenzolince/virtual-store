<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\Middleware\Authenticate;
use App\Models\Middleware\ValidRoles;
use App\Models\response\ResponseStore;
use App\Models\service\UserService;

$app->post('/' , function(Request $request, Response $response, array $args)  {
        $res=new ResponseStore();

         $user =json_decode($request->getBody());
         $userService = new UserService();
         $userService -> createUser($user);
        $msg = array();
        $msg["error"] = false;
        $msg["message"] = "ok";
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

})->add(new ValidRoles())
  ->add(new Authenticate());