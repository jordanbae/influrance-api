<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('agent_customer_order', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('order_id');
            $table->bigInteger('product_id')->nullable();
            $table->bigInteger('customer_id')->nullable();
            $table->bigInteger('agent_id')->nullable();
            $table->string('product_name')->nullable();
            $table->integer('product_price')->nullable();
            $table->integer('coverage_allowance')->nullable();
            $table->integer('coverage_claimed')->nullable()->default(0);
            $table->integer('coverage_left')->nullable();
            $table->string('prefix')->nullable();
            $table->string('firstname')->nullable();
            $table->string('lastname')->nullable();
            $table->string('citizen_id');
            $table->string('email');
            $table->string('address')->nullable();
            $table->string('district')->nullable();
            $table->string('sub_district')->nullable();
            $table->string('postal_code')->nullable();
            $table->string('province')->nullable();
            $table->date('birthdate')->nullable();
            $table->date('end_date')->nullable();
            $table->date('start_date')->nullable();
            $table->string('beneficiary_1_prefix')->nullable();
            $table->string('beneficiary_1_firstname')->nullable();
            $table->string('beneficiary_1_lastname')->nullable();
            $table->string('beneficiary_1_phone_number')->nullable();
            $table->string('beneficiary_1_relationship')->nullable();
            $table->string('beneficiary_2_prefix')->nullable();
            $table->string('beneficiary_2_firstname')->nullable();
            $table->string('beneficiary_2_lastname')->nullable();
            $table->string('beneficiary_2_phone_number')->nullable();
            $table->string('beneficiary_2_relationship')->nullable();
            $table->string('beneficiary_3_prefix')->nullable();
            $table->string('beneficiary_3_firstname')->nullable();
            $table->string('beneficiary_3_lastname')->nullable();
            $table->string('beneficiary_3_phone_number')->nullable();
            $table->string('beneficiary_3_relationship')->nullable();
            $table->date('purchase_date')->nullable();
            $table->string('status')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('agent_customer_order');
    }
};
