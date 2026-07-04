import React from 'react'

import { Center, Grid } from '@chakra-ui/react'

function Section({ children, ...props }) {
    return (
        <Center bg={'blue'} borderRadius={'xl'} >

            <Grid gap={1} m={{ base: 2, smToMd: 2, md: 3 }}
                templateRows={{ base: "repeat(9, 2rem)", smToMd: 'repeat(9, 2rem)', md: 'repeat(9, 3.5rem)' }}
                templateColumns={{ base: "repeat(9, 2rem)", smToMd: 'repeat(9, 2rem)', md: 'repeat(9, 3.5rem)' }}
                {...props}>{children}</Grid>

        </Center>
    )
}

export default Section