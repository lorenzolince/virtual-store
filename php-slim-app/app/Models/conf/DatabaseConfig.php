<?php

namespace App\Models\conf;

define('DB_USERNAME', getenv("DB_USERNAME"));
define('DB_PASSWORD', getenv("DB_PASSWORD"));
define('DB_HOST', getenv("DB_HOST"));
define('DB_NAME', getenv("DB_NAME"));
use \PDO;
class DatabaseConfig {
    private $conn;
 
    function __construct() { 
        
   } 
    
    function connect() {
       
        
      try {
            $this->conn = new PDO('mysql:host=' .DB_HOST.';dbname='.
                            DB_NAME.';charset=utf8', 
                            DB_USERNAME, 
                            DB_PASSWORD);

            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);

            return $this->conn;

        } catch(PDOException $ex) {

            // if the environment is development, show error details, otherwise show generic message
            if ( (defined('ENVIRONMENT')) && (ENVIRONMENT == 'development') ) {
                echo 'An error occured connecting to the database! Details: ' . $ex->getMessage();
            } else {
                echo 'An error occured connecting to the database!';
            }
            exit;
        }
        
    }
 
}