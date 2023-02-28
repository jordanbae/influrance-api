<!DOCTYPE html>
<html lang="en">

<head>
    <title>Health Policy</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="{{ public_path() . '/pdf/bootstrap.min.css' }}">
    <script src="{{ public_path() . '/pdf/jquery.min.js' }}"></script>
    <script src="{{ public_path() . '/pdf/bootstrap.min.js' }}"></script>
    <script src="{{ public_path() . '/pdf/bootstrap.bundle.min.js' }}"></script>
    <script src="{{ public_path() . '/pdf/popper.min.js' }}"></script>

    <style>
	

        .invoice-box table {
            width: 100%;
            line-height: inherit;
            text-align: left;
        }

        .invoice-box table td {
            padding: 5px;
            vertical-align: top;
        }

        .invoice-box table tr td:nth-child(2) {
            text-align: right;
        }

        .invoice-box table tr.top table td {
            padding-bottom: 20px;
        }

        .invoice-box table tr.top table td.title {
            font-size: 45px;
            line-height: 45px;
            color: #333;
        }

        .invoice-box table tr.information table td {
            padding-bottom: 40px;
        }

        .invoice-box table tr.heading td {
            background: #eee;
            border-bottom: 1px solid #ddd;
            font-weight: bold;
        }

        .invoice-box table tr.details td {
            padding-bottom: 20px;
        }

        .invoice-box table tr.item td {
            border-bottom: 1px solid #eee;
        }

        .invoice-box table tr.item.last td {
            border-bottom: none;
        }

        .invoice-box table tr.total td:nth-child(2) {
            border-top: 2px solid #eee;
            font-weight: bold;
        }

        @media only screen and (max-width: 600px) {
            .invoice-box table tr.top table td {
                width: 100%;
                display: block;
                text-align: center;
            }

            .invoice-box table tr.information table td {
                width: 100%;
                display: block;
                text-align: center;
            }
        }

        /** RTL **/
        .invoice-box.rtl {
            direction: rtl;
            font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
        }

        .invoice-box.rtl table {
            text-align: right;
        }

        .invoice-box.rtl table tr td:nth-child(2) {
            text-align: left;
        }
    </style>
</head>

<body>
    <div class="invoice-box">
        <table cellpadding="0" cellspacing="0">
            <tr class="top">
                <td colspan="2">
                    <table>
                        <tr>
                            <td class="title">
                                <img src="{{url('/img/invoice_logo.png')}}" alt="Image" style="width:60px; height:65px"/>
                            </td>
                            <td>
                              Date: {{$request['purchase_date']}}<br />
                            Invoice #: 0000{{$request['order_id']}}


                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr class="information">
              
                <td colspan="2">
                                                  <hr/>
                    <table>
                        <tr>
                          <td>      

                            <h3>Bill to:</h3>
                                Influrance Co., Ltd.<br />
                                101 Sukhumvit 101/1<br />
                                Bang Chak, Phra Khanong<br />
                                Bangkok 10260
                            </td>

                          <td>      

                            <h3>Ship to:</h3>
                            {{$request['prefix']}} {{$request['firstname']}} {{$request['lastname']}}<br />
                          {{$request['address']}}<br />
                          {{$request['district']}} {{$request['sub_district']}}<br />
                          {{$request['postal_code']}} {{$request['province']}}
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr class="heading">
                <td>Customer</td>

                <td></td>
            </tr>

            <tr class="details">
                            <td>{{$request['prefix']}} {{$request['firstname']}} {{$request['lastname']}}</td>
                <td></td>
            </tr>

            <tr class="heading">
                <td>Product</td>

                <td></td>

              
            </tr>

            <tr class="item">
              <td>Package</td>

                <td>{{$request['product_name']}}</td>
            </tr>
            <tr class="item">
              <td>Price</td>

                <td>{{$request['product_price']}}</td>
            </tr>

            <tr class="item">
                <td>Coverage Allowance</td>

                <td>{{$request['coverage_allowance']}}</td>
            </tr>
                          <tr class="item">
                <td>Issued Date</td>

                <td>{{$request['start_date']}}</td>
            </tr>
                          <tr class="item last">
                <td>Expire Date</td>

                <td>{{$request['end_date']}}</td>
            </tr>
          <tr class="heading">
                <td>Beneficiary 1</td>

                <td></td>
<tr class="item">
              <td>Name</td>

                <td>{{$request['beneficiary_1_firstname']}} {{$request['beneficiary_1_lastname']}} </td>
            </tr>

            <tr class="item">
                <td>Phone number</td>

                <td>{{$request['beneficiary_1_phone_number']}}</td>
            </tr>
                          <tr class="item">
                <td>Relationship</td>

                <td>{{$request['beneficiary_1_relationship']}}</td>
            </tr>
            
                          <tr class="heading">
                <td>Beneficiary 2</td>

                <td></td>
<tr class="item">
              <td>Name</td>

                <td>{{$request['beneficiary_2_firstname']}} {{$request['beneficiary_2_lastname']}} </td>
            </tr>

            <tr class="item">
                <td>Phone number</td>

                <td>{{$request['beneficiary_2_phone_number']}}</td>
            </tr>
                          <tr class="item">
                <td>Relationship</td>

                <td>{{$request['beneficiary_2_relationship']}}</td>
            </tr>
            
                          <tr class="heading">
                <td>Beneficiary 3</td>

                <td></td>
<tr class="item">
              <td>Name</td>

                <td>{{$request['beneficiary_3_firstname']}} {{$request['beneficiary_3_lastname']}} </td>
            </tr>

            <tr class="item">
                <td>Phone number</td>

                <td>{{$request['beneficiary_3_phone_number']}}</td>
            </tr>
                          <tr class="item">
                <td>Relationship</td>

                <td>{{$request['beneficiary_3_relationship']}}</td>
            </tr>
            
            <tr class="total">
                <td></td>

                <td>Total: {{$request['product_price']}}</td>
            </tr>
        </table>
    </div>
</body>
</html>