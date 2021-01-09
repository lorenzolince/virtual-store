<?php

$base_path = getenv("DOCUMENT_ROOT"). '/';

require $base_path . 'bootstrap/appLogin.php';

$app->run();

