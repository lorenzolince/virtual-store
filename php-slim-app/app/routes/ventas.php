<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\Middleware\Authenticate;
use App\Models\Middleware\ValidRoles;
use App\Models\response\ResponseStore;
use App\Models\service\VentasService;

$app->post('/save' , function(Request $request, Response $response, array $args)  {
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

    $app->get('/get/all' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $vService = new VentasService();
        $msg = $vService -> getAll();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;


    $app->get('/get/estados' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $vService = new VentasService();
        $msg = $vService -> getEstados();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;

      $app->get('/get/ventas' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $vService = new VentasService();
        $msg = $vService -> getAllVentas();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;
      $app->get('/get/productos/{id}' , function(Request $request, Response $response, array $args)  {
         $res=new ResponseStore();
         $vService = new VentasService();
         $msg = $vService -> getProductosByVenta($args["id"]);
         $response->getBody()->write(json_encode($msg));
         return $res->echoResponse(200,$response);

}) ->add(new ValidRoles())
   ->add(new Authenticate())
  ;
  $app->post('/update/Estado/{id}/{estado}' , function(Request $request, Response $response, array $args)  {
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

