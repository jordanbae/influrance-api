<?php

namespace App\Http\Controllers\API\v1;

use App\Http\Controllers\Controller;
use App\Models\Customer;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Mail;
use App\Mail\Register;
use Tymon\JWTAuth\Facades\JWTAuth;
use Illuminate\Support\Facades\DB;

class CustomerController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): Response
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(): Response
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        //
        $status = "success";
        $resHttp = 200;
        $data = $request->all();
        $data['password'] = bcrypt($data['password']);
        Customer::create($data);
        Mail::to("jordanlaphon@gmail.com")->send(new Register($request->username,$request->password));
        return response()->json([
            "status"=>$status,
            "response"=>$resHttp,
            "data"=>$data
        ], $resHttp);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id): Response
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id): Response
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id): RedirectResponse
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id): RedirectResponse
    {
        //
    }
    public function customerLogin(Request $req){
        $username = $req->username;
        $status = "success";
        $respHttp = 200;
        $customer = Customer::where("username", $username)->first();
        if($customer){
            $pass = $req->password;
            if(\Hash::check($pass, $customer->password)){
                return response()->json([
                    "status" => "LoggedIn",
                    "response" => $respHttp,
                    "register" => $customer,
                ], $respHttp);
            } else {
                $status = "Please check your username or password";
                $respHttp = 401;
            }
        } else {
            $status = "error";
            $respHttp = 401;
        }

        return response()->json([
            "status" => $status,
            "response" => $respHttp
        ], $respHttp);
    }

    public function getMaxId(){
        $maxId = DB::table('customer')->max('id');
        return response()->json(['nextId' => $maxId + 1]);
    }
}
