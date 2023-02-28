<x-mail::message>
# Thank you for registering with Influrance ! 

Here are your login credentials.
<div>Username: {{$username}}</div>
<div>Password: {{$password}}
<x-mail::button :url="''">
Visit
</x-mail::button>

Thanks,<br>
{{ config('app.name') }}
</x-mail::message>
