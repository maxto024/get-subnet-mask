# get-subnet-mask
React Native Subnet Mask for IOS and Android

## Usage
 getSubnet() return the subnet mask value.
```
import SubnetmaskModule from ‘get-subnet-mask’;

SubnetmaskModule.getSubnet((sb) => { 

    console.log(sb);

});

```

getIpV4() return the IP value.
```
import SubnetmaskModule from ‘get-subnet-mask’;

SubnetmaskModule.getIpV4((ip) => { 

    console.log(ip);

});

```

##Install

1. `npm install get-subnet-mask`
2. `react-native link`


# License
MIT
