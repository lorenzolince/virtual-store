<?php

namespace App\Models\response;

class ResponseStore 
{	
  function echoResponse($status_code, $response) {
  return $response ->withStatus($status_code)
          ->withHeader('Content-Type', 'application/json');
   }
}