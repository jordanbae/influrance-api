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
        Schema::create('agent_customer', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('agent_id');
            $table->bigInteger('customer_id')->nullable();
            $table->string('customer_prefix');
            $table->string('customer_firstname');
            $table->string('customer_lastname');
            $table->string('customer_email')->unique();
            $table->string('customer_phone_number')->unique();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('agent_customer');
    }
};
