<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class AgentCustomer extends Model
{
    use HasFactory;
    public $timestamps = true;

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'agent_customer';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'agent_id',
        'customer_id',
        'customer_prefix',
        'customer_firstname',
        'customer_lastname',
        'customer_email',
        'customer_phone_number',
    ];
    
}
