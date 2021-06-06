<?php

/** @var \Laravel\Lumen\Routing\Router $router */

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

// $router->group(['prefix' => 'api/user', 'middleware' => ['login']], function () use ($router) {

//     $router->get('/', 'UserController@index');

//     $router->get('/{username}', 'UserController@getByUsername');

//     $router->put('/{username}', 'UserController@editUser');
// });

$router->group(['prefix' => 'api/book'], function () use ($router) {

    $router->get('/', 'BooksController@index');

    // $router->get('/id/{id}', 'BooksController@getId');

    // $router->get('/judul/{judul}', 'BooksController@getJudul');

    // $router->post('/', 'BooksController@createBuku');

    // $router->put('/{id}', 'BooksController@updateBuku');

    // $router->delete('/{id}', 'BooksController@deletebyId');
});

$router->group(['prefix' => 'api/pinjam'], function () use ($router) {

    $router->get('/{id_user}', 'PeminjamanController@showPinjaman');

    $router->post('/', 'PeminjamanController@store');

    $router->delete('/{id_user}/{id_buku}', 'PeminjamanController@destroy');
});

// $router->group(['prefix' => 'api'], function () use ($router) {

//     $router->post("/register", "AuthController@register");

//     $router->post("/login", "AuthController@login");

//     $router->post("/logout", "AuthController@logout");
// });