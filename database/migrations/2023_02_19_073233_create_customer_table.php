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
        Schema::create('customer', function (Blueprint $table) {
            $table->id();
            $table->string('application_number')->unique();
            $table->date('registration_date');
            $table->string('prefix');
            $table->string('firstname');
            $table->string('lastname');
            $table->string('citizen_id')->unique();
            $table->string('email')->unique();
            $table->string('username')->unique();
            $table->string('password');
            $table->string('phone_number')->unique();
            $table->string('address');
            $table->string('district');
            $table->string('sub_district');
            $table->string('postal_code');
            $table->string('province');
            $table->date('birthdate');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('customer');
    }
};
