<?php
ini_set('session.gc_maxlifetime', 1800);
session_set_cookie_params(1800);
session_start();

header("Access-Control-Allow-Origin: *");
header('Access-Control-Allow-Credentials: true');
header('Access-Control-Allow-Methods: PUT, GET, POST, DELETE, OPTIONS');
header("Access-Control-Allow-Headers: X-Requested-With");
header('Content-Type: text/html; charset=utf-8');
header('P3P: CP="IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA"'); 

$base_path = getenv("DOCUMENT_ROOT"). '/';

require $base_path . 'vendor/autoload.php';

$app = new \Slim\App([
  'settings' => [
   'displayErrorDetails' => true,
  ]
]);

