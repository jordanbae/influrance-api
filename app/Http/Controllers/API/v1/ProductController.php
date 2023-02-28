<?php

namespace App\Http\Controllers\API\v1;

use App\Http\Controllers\Controller;
use App\Models\AgentCustomerOrder;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Models\Product;

class ProductController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(Request $req)
    {
        //
        $status = "success";
        $respHttp = 200;

        // $registers = RegisterFull::skip(0)->take(10);
        $registers = Product::skip(0)->take(10)->get();
        $countTotal = Product::count();

        return response()->json([
            "status" => $status,
            "response" => $respHttp,
            "count_total" => $countTotal,
            "data" => $registers,
        ], $respHttp);
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

    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
        $status ="success";
        $resHTTP = 200;
        $register = Product::find($id);
        // dd($register);
        return response()->json([
            "status" => $status,
            "response" => $resHTTP,
            "data" => $register
        ], $resHTTP);
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
}
