<?php
namespace App\Models\service;

use App\Models\dao\ArticlesDao;
use Psr\Http\Message\UploadedFileInterface as UploadedFile;

class ArticlesService  {

    private  $articleDao;
     
     function __construct() {
      $this->$articleDao = new ArticlesDao();
    }

    public function save($request , $uploadedFile)
    {
     $articles = (object)[];
     $articles->id =  $request->getParam('id');
     $articles->name=  $request->getParam('name');
     $articles->url=  $request->getParam('url');
     $articles->video= $request->getParam('video'); 
     $articles->description= $request->getParam('description');
     $articles->precio= $request->getParam('precio');
     $articles->categoria= $request->getParam('categoria');
     $articles->precioReal= $request->getParam('precioReal');
     $articles->cantidad= $request->getParam('cantidad');
      if($articles->id==null){ 
     $base_path = getenv("DOCUMENT_ROOT"). '/';
     $directory = $base_path . "public";
     error_log ($directory);
     $filename =  $this-> moveUploadedFile($directory,$uploadedFile);
     error_log ($filename);
     $protocol = stripos($_SERVER['SERVER_PROTOCOL'],'https') === 0 ? 'https://' : 'http://';
     $articles->url= $protocol . $_SERVER["HTTP_HOST"]. '/public/'. $filename;
     error_log ($articles->url);
    return $this->$articleDao->save($articles);
} else {

    return $this->$articleDao->update($articles);  
}
    }
     public function saveData($request)
    {
     $articles = (object)[];
     $articles->id =  $request->getParam('id');
     $articles->name=  $request->getParam('name');
     $articles->url=  $request->getParam('url');
     $articles->video= $request->getParam('video'); 
     $articles->description= $request->getParam('description');
     $articles->precio= $request->getParam('precio');
     $articles->categoria= $request->getParam('categoria');
     $articles->precioReal= $request->getParam('precioReal');
     $articles->cantidad= $request->getParam('cantidad');
       if($articles->id==null){ 
     return $this->$articleDao->save($articles);
      } else {

      return $this->$articleDao->update($articles);  
      }
    }

    public function delete($_id)
     {
     $this->$articleDao->delete($_id);
     }

    public function get($_id)
     {
      return $this->$articleDao->get($_id);
     }

    public function getAll()
     {
      return $this->$articleDao->getAll();
     }

    public function byCategory($category)
     {
      return $this->$articleDao->byCategory($category);
     }

  public  function moveUploadedFile($directory, UploadedFile $uploadedFile)
 { 
    $extension = pathinfo($uploadedFile->getClientFilename(), PATHINFO_EXTENSION);
    $basename = bin2hex(random_bytes(8)); 
    $filename = sprintf('%s.%0.8s', $basename, $extension);
    error_log ($filename);
    $uploadedFile->moveTo($directory . DIRECTORY_SEPARATOR . $filename);
    return $filename;
  }

}