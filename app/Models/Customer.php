<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Tymon\JWTAuth\Contracts\JWTSubject;
use Tymon\JWTAuth\Contracts\Providers\JWT;

class Customer extends Authenticatable implements JWTSubject
{
    use HasFactory;
    
    public $timestamps = true;

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'customer';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'application_number',
        'registration_date',
        'prefix',
        'firstname',
        'lastname',
        'citizen_id',
        'email',
        'username',
        'password',
        'phone_number',
        'address',
        'district',
        'sub_district',
        'postal_code',
        'province',
        'birthdate',
    ];

    public function getJWTIdentifier(){
        return $this->getKey();
    }
    public function getJWTCustomClaims(){
        return [];
    }
}
