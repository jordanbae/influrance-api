<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Agent extends Model
{
    use HasFactory;
    
    public $timestamps = true;

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'agent';

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
        'role',
        'license_number',
        'license_expire_date'
    ];
}
