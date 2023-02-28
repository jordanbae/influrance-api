<?php

namespace App\Http\Controllers\API\v1;

use App\Http\Controllers\Controller;
use App\Models\AgentCustomerOrder;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Tymon\JWTAuth\Facades\JWTAuth;
use App\Mail\Purchase;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\DB;

class AgentCustomerOrderController extends Controller
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
        // dd($data);
        AgentCustomerOrder::create($data);
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

    public function getOrderId() {
        $maxId = DB::table('agent_customer_order')->max('id');
        return response()->json(['nextOrder' => $maxId+1]);
    }
    public function getUserPurchases(Request $request){
        $token = $request->header('Authorization');
        JWTAuth::setToken($token);
        $user = JWTAuth::user();
        $customerOrders = AgentCustomerOrder::where('customer_id', $user->id)->get();
        return response()->json(["customerOrders" => $customerOrders]);
    }

    public function sendOrderDetail(Request $request){
        $email = $request->email;
        Mail::to('jordanlaphon@gmail.com')->send(new Purchase($request ->all()));
    }
}
