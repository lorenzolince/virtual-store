<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\response\ResponseStore;
use App\Models\Middleware\Authenticate;
use App\Models\Middleware\ValidRoles;
use App\Models\service\AuthenticationManager;
use App\Models\service\ArticlesService;
use App\Models\service\UserService;
use App\Models\service\VentasService;


// ################ auth ##################
$app->post('/auth/login' , function(Request $request, Response $response, array $args)  {
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


$app->get('/auth/logout' , function(Request $request, Response $response, array $args)  {
	      session_destroy();
        session_start();
        $res=new ResponseStore();
        $msg = array();
        $msg["message"] = "logout";
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);
});


// #############  articles #############

$app->post('/articles/save/file' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
         $msg = array();
        $uploadedFiles = $request->getUploadedFiles();
        $uploadedFile = $uploadedFiles['image'];
        $articleService = new ArticlesService();
        $msg["id"] = $articleService -> save($request,$uploadedFile);
        
        $msg["error"] = false;
        $msg["message"] = "ok";
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;

$app->post('/articles/save' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
       
        $msg = array();
        $msg["error"] = false;
        $msg["message"] = "ok";
        $articleService = new ArticlesService();
        $msg["id"] =  $articleService -> saveData($request);
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;
  $app->post('/articles/delete' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $_id =$request->getParam('id');
        $msg = array();
        $msg["error"] = false;
        $msg["message"] = "ok";
        $articleService = new ArticlesService();
        $articleService -> delete($_id);
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;
 $app->get('/articles/get' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $_id =$request->getParam('id');
        $articleService = new ArticlesService();
        $msg = $articleService -> get($_id);
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

});
  $app->get('/articles/get/all' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $articleService = new ArticlesService();
        $msg = $articleService -> getAll();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

});
  $app->get('/articles/get/byCategory' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $category =$request->getParam('category');
        $articleService = new ArticlesService();
        $msg = $articleService -> byCategory($category);
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

});
// ################ register ##################
$app->post('/register' , function(Request $request, Response $response, array $args)  {
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
// ################ ventas ##################
$app->post('/ventas/save' , function(Request $request, Response $response, array $args)  {
         $res=new ResponseStore();
         $msg = array();
         $ventas =json_decode($request->getBody());
         $vService = new VentasService();
         $msg["id"] =  $vService -> save($ventas);
         $msg["error"] = false;
         $msg["message"] = "ok";
         $response->getBody()->write(json_encode($msg));
         return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;

    $app->get('/ventas/get/all' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $vService = new VentasService();
        $msg = $vService -> getAll();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;


    $app->get('/ventas/get/estados' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $vService = new VentasService();
        $msg = $vService -> getEstados();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;

      $app->get('/ventas/get/ventas' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $vService = new VentasService();
        $msg = $vService -> getAllVentas();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;
      $app->get('/ventas/get/productos/{id}' , function(Request $request, Response $response, array $args)  {
         $res=new ResponseStore();
         $vService = new VentasService();
         $msg = $vService -> getProductosByVenta($args["id"]);
         $response->getBody()->write(json_encode($msg));
         return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;
  $app->post('/ventas/update/Estado/{id}/{estado}' , function(Request $request, Response $response, array $args)  {
         $res=new ResponseStore();
         $msg = array();
         $vService = new VentasService();
         $vService -> updateEstadoVenta($args["id"],$args["estado"]);
         $msg["error"] = false;
         $msg["message"] = "ok";
         $response->getBody()->write(json_encode($msg));
         return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;

