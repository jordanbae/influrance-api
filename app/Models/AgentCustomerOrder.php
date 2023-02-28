<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class AgentCustomerOrder extends Model
{
    use HasFactory;
    public $timestamps = true;

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'agent_customer_order';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'order_id',
        'product_id',
        'agent_id',
        'customer_id',
        'product_name',
        'product_price',
        'coverage_allowance',
        'coverage_claimed',
        'coverage_left',
        'prefix',
        'firstname',
        'lastname',
        'citizen_id',
        'email',
        'birthdate',
        'start_date',
        'end_date',
        'address',
        'district',
        'sub_district',
        'postal_code',
        'province',
        'beneficiary_1_prefix',
        'beneficiary_1_firstname',
        'beneficiary_1_lastname',
        'beneficiary_1_phone_number',
        'beneficiary_1_relationship',
        'beneficiary_2_prefix',
        'beneficiary_2_firstname',
        'beneficiary_2_lastname',
        'beneficiary_2_phone_number',
        'beneficiary_2_relationship',
        'beneficiary_3_prefix',
        'beneficiary_3_firstname',
        'beneficiary_3_lastname',
        'beneficiary_3_phone_number',
        'beneficiary_3_relationship',
        'purchase_date',
        'status'
    ];
    
}
