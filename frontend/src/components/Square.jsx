import React from 'react'
import { Center } from '@chakra-ui/react'

function Square({ children, ...props }) {
    return (
        <Center borderRadius={'md'} bg={'red'} minW={'2rem'} minH={'2rem'} {...props}>{children}</Center>
    )
}

export default Square