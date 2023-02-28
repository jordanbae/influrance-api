<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\API\v1\CustomerController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::resource('/v1/customer', 'API\v1\CustomerController');
Route::post('/v1/customer/login', 'API\v1\CustomerController@customerLogin');
Route::post('/v1/customer/getnext', 'API\v1\CustomerController@getMaxId');

//Product
Route::resource('/v1/product', 'API\v1\ProductController');
Route::resource('/v1/order', 'API\v1\AgentCustomerOrderController');
Route::post('/v1/uorder', 'API\v1\AgentCustomerOrderController@getUserPurchases');
Route::post('/v1/order/getnextorder', 'API\v1\AgentCustomerOrderController@getOrderId');
// Route::resource('/v1/customer', CustomerController::class);

//Auth
Route::post('/v1/auth/register', 'API\v1\AuthController@register');
Route::post('/v1/auth/login', 'API\v1\AuthController@login');
// Route::get('/v1/auth/getusername', 'API\v1\AuthController@getUsername');
Route::post('/v1/auth/getname', 'API\v1\AuthController@getUserForPurchase');




Route::post('/v1/order/detail', 'API\v1\AgentCustomerOrderController@sendOrderDetail');