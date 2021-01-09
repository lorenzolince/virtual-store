<?php

$base_path = getenv("DOCUMENT_ROOT"). '/';

require $base_path . 'bootstrap/appRegister.php';

$app->run();