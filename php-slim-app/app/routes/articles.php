<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use App\Models\Middleware\Authenticate;
use App\Models\Middleware\ValidRoles;
use App\Models\response\ResponseStore;
use App\Models\service\ArticlesService;


$app->post('/save/file' , function(Request $request, Response $response)  {
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

$app->post('/save' , function(Request $request, Response $response)  {
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
  $app->post('/delete' , function(Request $request, Response $response)  {
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
 $app->get('/get' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $_id =$request->getParam('id');
        $articleService = new ArticlesService();
        $msg = $articleService -> get($_id);
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

});
  $app->get('/get/all' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $articleService = new ArticlesService();
        $msg = $articleService -> getAll();
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

});
  $app->get('/get/byCategory' , function(Request $request, Response $response)  {
        $res=new ResponseStore();
        $category =$request->getParam('category');
        $articleService = new ArticlesService();
        $msg = $articleService -> byCategory($category);
        $response->getBody()->write(json_encode($msg));
        return $res->echoResponse(200,$response);

});
function moveUploadedFile($directory, UploadedFile $uploadedFile)
{
    $extension = pathinfo($uploadedFile->getClientFilename(), PATHINFO_EXTENSION);
    $basename = bin2hex(random_bytes(8)); // see http://php.net/manual/en/function.random-bytes.php
    $filename = sprintf('%s.%0.8s', $basename, $extension);

    $uploadedFile->moveTo($directory . DIRECTORY_SEPARATOR . $filename);

    return $filename;
}
